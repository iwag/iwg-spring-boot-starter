package biz.iwag.blog.service;

import biz.iwag.blog.config.ApplicationProperties;
import biz.iwag.blog.config.Constants;
import biz.iwag.blog.repository.GameDataRedisRepository;
import biz.iwag.blog.repository.HelperRedisRepository;
import biz.iwag.blog.service.dto.VersionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;


@SuppressWarnings("unused")
@Service
public class VersionService {
    static int LAST_API_VERSION = 34;
    static int LAST_BUILD_NUMVER = 214;
    static String LAST_APP_VERSION = "1.17.0";

    @Autowired
    ApplicationProperties applicationProperties;

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
            lastBuildNumber = helperRedisRepository.get(Constants.LAST_BUILD_VERSION).map(Integer::valueOf).orElse(LAST_API_VERSION);
            lastApplicationVersion = helperRedisRepository.get(Constants.LAST_APP_VERSION).orElse(LAST_APP_VERSION);
        }

        String localizationTimestampStr = gameDataRedisRepository.get("v" + Integer.toString(api) + ":internationalization:timestamp");
        Long localizationTimestamp = localizationTimestampStr!=null && !localizationTimestampStr.isEmpty() ? Long.valueOf(localizationTimestampStr) : 0;
        String gamedataTimestampStr = gameDataRedisRepository.get("v" +Integer.toString(api)+ ":game_data:timestamp");
        Long gamedataTimestamp = gamedataTimestampStr != null && !gamedataTimestampStr.isEmpty() ? Long.valueOf(gamedataTimestampStr) : 0;

        String cacheBaseUrl = applicationProperties.getCaches().getCache_base_url_path();
        Boolean maintenanceWarning = false;
        Boolean enableDebug = isDebugDevice(deviceId);
        String downloadUrl = getDownloadUrl(platform);
        return new VersionDTO(api, true, lastApi,downloadUrl, newVersionWarning, maintenanceWarning, enableDebug, getBundleVariant(deviceModel, screenHeight, maxTextureSize), localizationTimestamp, gamedataTimestamp, cacheBaseUrl, lastBuildNumber, lastApplicationVersion);
    }

    private Boolean isDebugDevice(String deviceId) {
        List<String> list = helperRedisRepository.getList("settings:sr_debug_devices");
        return list.stream().anyMatch(s -> s.equals(deviceId));
    }

    private String getBundleVariant(@NotNull String deviceModel, Integer screenHeight, Integer maxTextureSize) {

        String lowered = deviceModel.replace(" ", "_").toLowerCase();
        Optional<String> mapped = helperRedisRepository.getValue("settings", "bundle_variants:" + lowered);
        //Map<String, String> mapped = helperRedisRepository.getMap("bundle_variants" + lowered);
        String variant = Optional.of(mapped.orElse("sd")).orElse(screenHeight <= 768 ? "sd" : (maxTextureSize < 4096 ? "hd" : "sd"));

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

    private String getDownloadUrl(String platform){
        if (platform!=null){
            Optional<String> url = helperRedisRepository.getValue("settings", "download_url:" + platform.toLowerCase());
            return url.orElse("http://hempiregame.com");
        }
        return "http://hempiregame.com";
    }

    private Integer getApiBy(String platform, String buildVersion) {
        return mappedVersion.getOrDefault(buildVersion, 0);
    }

    private Integer lastUpdatedApi() {
        Optional<String> s = helperRedisRepository.get(Constants.LAST_API_VERSION);
        return s.map(i -> Integer.valueOf(i)).orElse(LAST_API_VERSION);
    }
}
