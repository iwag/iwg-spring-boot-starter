package biz.iwag.blog.service.dto;

import io.swagger.models.auth.In;

public class VersionDTO {
    private Integer api_version;
    private Boolean returning_default_api;
    private Integer last_api_version;
    private String download_url;
    private Boolean new_version_warning;
    private Boolean maintenance_warning;
    private Boolean enable_debug;
    private String bundle_variant;
    private Long localization_timestamp;
    private Long gamedata_timestamp;
    private String cache_base_url_path;

    public VersionDTO() {
    }

    public VersionDTO(Integer api, Integer lastApi, boolean newVersionWarning, Integer lastBuildNumber, String lastApplicationVersion) {
        this.api_version = api;
        this.returning_default_api = false;
        this.last_api_version = lastApi;
        this.new_version_warning = newVersionWarning;
    }

    public Integer getApi_version() {
        return api_version;
    }

    public void setApi_version(Integer api_version) {
        this.api_version = api_version;
    }

    public Boolean getReturning_default_api() {
        return returning_default_api;
    }

    public void setReturning_default_api(Boolean returning_default_api) {
        this.returning_default_api = returning_default_api;
    }

    public Integer getLast_api_version() {
        return last_api_version;
    }

    public void setLast_api_version(Integer last_api_version) {
        this.last_api_version = last_api_version;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public Boolean getNew_version_warning() {
        return new_version_warning;
    }

    public void setNew_version_warning(Boolean new_version_warning) {
        this.new_version_warning = new_version_warning;
    }

    public Boolean getMaintenance_warning() {
        return maintenance_warning;
    }

    public void setMaintenance_warning(Boolean maintenance_warning) {
        this.maintenance_warning = maintenance_warning;
    }

    public Boolean getEnable_debug() {
        return enable_debug;
    }

    public void setEnable_debug(Boolean enable_debug) {
        this.enable_debug = enable_debug;
    }

    public String getBundle_variant() {
        return bundle_variant;
    }

    public void setBundle_variant(String bundle_variant) {
        this.bundle_variant = bundle_variant;
    }

    public Long getLocalization_timestamp() {
        return localization_timestamp;
    }

    public void setLocalization_timestamp(Long localization_timestamp) {
        this.localization_timestamp = localization_timestamp;
    }

    public Long getGamedata_timestamp() {
        return gamedata_timestamp;
    }

    public void setGamedata_timestamp(Long gamedata_timestamp) {
        this.gamedata_timestamp = gamedata_timestamp;
    }

    public String getCache_base_url_path() {
        return cache_base_url_path;
    }

    public void setCache_base_url_path(String cache_base_url_path) {
        this.cache_base_url_path = cache_base_url_path;
    }
}
