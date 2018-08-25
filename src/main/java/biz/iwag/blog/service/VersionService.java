package biz.iwag.blog.service;

import biz.iwag.blog.config.Constants;
import biz.iwag.blog.domain.GeneralSettings;
import biz.iwag.blog.domain.Version;
import biz.iwag.blog.repository.GeneralSettingsRepository;
import biz.iwag.blog.service.dto.VersionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
@Service
public class VersionService {

    @Autowired
    GeneralSettingsRepository generalSettingsRepo;
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

    public VersionDTO getVersionInfo(String platform, String buildVersion) {

        Integer api = getApiBy(platform, buildVersion);
        Integer lastApi = lastUpdatedApi();

        boolean newVersionWarning = false;
        Integer lastBuildNumber = null;
        String lastApplicationVersion = null;
        if (api < lastApi) {
            newVersionWarning = true;
            lastBuildNumber = 215;
            lastApplicationVersion = "1.7.1";
        }

        return new VersionDTO(api, lastApi,newVersionWarning, lastBuildNumber, lastApplicationVersion);
    }

    private Integer getApiBy(String platform, String buildVersion) {
        return mappedVersion.getOrDefault(buildVersion, 0);
    }

    private Integer lastUpdatedApi() {
        String s = generalSettingsRepo.get(Constants.LAST_API_VERSION);
        return Integer.valueOf(s);
    }
}
