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
    private Integer last_build_number;
    private String last_application_version;

    public VersionDTO() {
    }

    public VersionDTO(Integer api_version, Boolean returning_default_api, Integer last_api_version, String download_url, Boolean new_version_warning, Boolean maintenance_warning, Boolean enable_debug, String bundle_variant, Long localization_timestamp, Long gamedata_timestamp, String cache_base_url_path, Integer last_build_number, String last_application_version) {
        this.api_version = api_version;
        this.returning_default_api = returning_default_api;
        this.last_api_version = last_api_version;
        this.download_url = download_url;
        this.new_version_warning = new_version_warning;
        this.maintenance_warning = maintenance_warning;
        this.enable_debug = enable_debug;
        this.bundle_variant = bundle_variant;
        this.localization_timestamp = localization_timestamp;
        this.gamedata_timestamp = gamedata_timestamp;
        this.cache_base_url_path = cache_base_url_path;
        this.last_build_number = last_build_number;
        this.last_application_version = last_application_version;
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

    public Integer getLast_build_number() {
        return last_build_number;
    }

    public void setLast_build_number(Integer last_build_number) {
        this.last_build_number = last_build_number;
    }

    public String getLast_application_version() {
        return last_application_version;
    }

    public void setLast_application_version(String last_application_version) {
        this.last_application_version = last_application_version;
    }
}
