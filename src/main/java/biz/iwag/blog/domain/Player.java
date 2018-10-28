package biz.iwag.blog.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.nio.file.FileStore;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Player.
 */
@Entity
@Table(name = "player_java")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "fb_user_id")
    private String fb_user_id;

    @Column(name = "enable_feedback")
    private Boolean enable_feedback;

    @Column(name = "creation_date")
    private LocalDate creation_date;

    @Column(name = "disabled")
    private Boolean disabled;

    @Column(name = "ab")
    private String ab;

    @Column(name = "hacker")
    private Boolean hacker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Player experience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player name(String name) {
        this.name = name;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }



    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Player deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getFb_user_id() {
        return fb_user_id;
    }

    public void setFb_user_id(String fb_user_id) {
        this.fb_user_id = fb_user_id;
    }

    public Player fbuserid(String fbuserid) {
        this.fb_user_id = fbuserid;
        return this;
    }

    public Boolean getEnable_feedback() {
        return enable_feedback;
    }

    public void setEnable_feedback(Boolean enable_feedback) {
        this.enable_feedback = enable_feedback;
    }

    public Player enablefeedback(Boolean enablefeedback) {
        this.enable_feedback = enablefeedback;
        return this;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public Player creationdate(LocalDate creationdate) {
        this.creation_date = creationdate;
        return this;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public Player disabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }


    public Player ab(String ab) {
        this.ab = ab;
        return this;
    }

    public Boolean isHacker() {
        return hacker;
    }

    public void setHacker(Boolean hacker) {
        this.hacker = hacker;
    }

    public Player hacker(Boolean hacker) {
        this.hacker = hacker;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        if (player.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), player.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Player{" +
            "id=" + id +
            ", experience=" + experience +
            ", name='" + name + '\'' +
            ", deviceId='" + deviceId + '\'' +
            ", fb_user_id='" + fb_user_id + '\'' +
            ", enable_feedback=" + enable_feedback +
            ", creation_date=" + creation_date +
            ", disabled=" + disabled +
            ", ab='" + ab + '\'' +
            ", hacker=" + hacker +
            '}';
    }
}
