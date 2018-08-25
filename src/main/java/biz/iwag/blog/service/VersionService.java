package biz.iwag.blog.service;

import biz.iwag.blog.config.Constants;
import biz.iwag.blog.domain.GeneralSettings;
import biz.iwag.blog.domain.Version;
import biz.iwag.blog.repository.GameDataRedisRepository;
import biz.iwag.blog.repository.GeneralSettingsRepository;
import biz.iwag.blog.repository.HelperRedisRepository;
import biz.iwag.blog.service.dto.VersionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;


@SuppressWarnings("unused")
@Service
public class VersionService {

    @Autowired
    GeneralSettingsRepository generalSettingsRepo;

    @Autowired
    GameDataRedisRepository gameDataRedisRepository;

    @Autowired
    HelperRedisRepository helperRedisRepository;

    private static Map<String, Integer> mappedVersion;
    static {
        mappedVersion = new HashMap<>();
        mappedVersion.put("230", 36);
        mappedVersion.put("240", 37);
        mappedVersion.put("250", 38);
        mappedVersion.put("251", 38);
        mappedVersion.put("252", 38);
        mappedVersion.put("253", 38);
        mappedVersion.put("254", 38);
    }

    public VersionDTO getVersionInfo(String platform, String buildVersion, String deviceId, String deviceModel, Integer screenHeight, Integer maxTextureSize) {

        Integer api = getApiBy(platform, buildVersion);
        Integer lastApi = lastUpdatedApi();

        boolean newVersionWarning = false;
        Integer lastBuildNumber = null;
        String lastApplicationVersion = null;
        if (api < lastApi) {
            newVersionWarning = true;
            lastBuildNumber = Integer.valueOf(generalSettingsRepo.get(Constants.LAST_BUILD_VERSION));
            lastApplicationVersion = generalSettingsRepo.get(Constants.LAST_APP_VERSION);
        }

        Long localizationTimestamp = Long.valueOf(gameDataRedisRepository.get(Integer.toString(api) + ":internationalization:timestamp"));
        Long gamedataTimestamp = Long.valueOf(gameDataRedisRepository.get(Integer.toString(api) + ":game_data:timestamp"));

        String cacheBaseUrl = ""; // CONFIG.get_cache_base_url
        Boolean maintenanceWarning = false;
        Boolean enableDebug = isDebugDevice(deviceId);
        String downloadUrl = helperRedisRepository.get("download_url" + platform.toLowerCase());
        return new VersionDTO(api, true, lastApi,downloadUrl, newVersionWarning, maintenanceWarning, enableDebug, getBundleVarient(deviceModel, screenHeight, maxTextureSize), localizationTimestamp, gamedataTimestamp, cacheBaseUrl, lastBuildNumber, lastApplicationVersion);
    }

    private Boolean isDebugDevice(String deviceId) {
        List<String> list = helperRedisRepository.getList("settings:sr_debug_devices");
        return list.stream().anyMatch(s -> s.equals(deviceId));
    }

    private String getBundleVarient(@NotNull String deviceModel, Integer screenHeight, Integer maxTextureSize) {

        String lowered = deviceModel.replace(" ", "_").toLowerCase();
        Map<String, String> mapped = helperRedisRepository.getMap("bundle_variants" + lowered);
        String variant = Optional.of(mapped.getOrDefault(deviceModel, "sd")).orElse(screenHeight <= 768 ? "sd" : (maxTextureSize < 4096 ? "hd" : "sd"));

        return variant;
//        $bundle_variant = null;
//        $settings_device_model = str_replace(' ', '_', strtolower($device_model));
//        if ($device_model && $this->redis->hExists("settings", "bundle_variants:$settings_device_model")){
//            $bundle_variant = $this->redis->hGet("settings", "bundle_variants:$settings_device_model");
//        }
//
//        if (!$bundle_variant && $max_texture_size){
//            if ($screen_height <= 768){ // 1536/2 metade da resolução y do iPad
//                $bundle_variant = "sd";
//            } else if ($max_texture_size < 4096){
//                $bundle_variant = "hd";
//            }
//        }
//
//        //if variant not defined or not allowed, set sd
//        $allowed_variants = ["sd", "hd", "hd4096"];
//        if (!$bundle_variant){
//            $bundle_variant = "hd4096";
//        }
//        if (!in_array($bundle_variant, $allowed_variants)){
//            $bundle_variant = "sd";
//        }
//        $response["bundle_variant"] = $bundle_variant;
    }

    private Integer getApiBy(String platform, String buildVersion) {
        return mappedVersion.getOrDefault(buildVersion, 0);
    }

    private Integer lastUpdatedApi() {
        String s = generalSettingsRepo.get(Constants.LAST_API_VERSION);
        return Integer.valueOf(s);
    }
}
