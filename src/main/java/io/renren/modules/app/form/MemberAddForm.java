package io.renren.modules.app.form;

import io.renren.modules.member.entity.*;

import java.util.List;

public class MemberAddForm {

    private List<MemberDrugEntity> drugs;
    private List<MemberCurrentEntity> currentEntities;
    private List<MemberLifeEntity> lifeEntities;
    private List<MemberLevelEntity> levelEntities;
    private List<MemberAllergyEntity> allergyEntities;
    private List<MemberCheckEntity> checkEntities;
    private List<MemberChildEntity> childEntities;
    private MemberEntity memberEntity;

    public List<MemberDrugEntity> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<MemberDrugEntity> drugs) {
        this.drugs = drugs;
    }

    public List<MemberCurrentEntity> getCurrentEntities() {
        return currentEntities;
    }

    public void setCurrentEntities(List<MemberCurrentEntity> currentEntities) {
        this.currentEntities = currentEntities;
    }

    public List<MemberLifeEntity> getLifeEntities() {
        return lifeEntities;
    }

    public void setLifeEntities(List<MemberLifeEntity> lifeEntities) {
        this.lifeEntities = lifeEntities;
    }

    public List<MemberLevelEntity> getLevelEntities() {
        return levelEntities;
    }

    public void setLevelEntities(List<MemberLevelEntity> levelEntities) {
        this.levelEntities = levelEntities;
    }

    public List<MemberAllergyEntity> getAllergyEntities() {
        return allergyEntities;
    }

    public void setAllergyEntities(List<MemberAllergyEntity> allergyEntities) {
        this.allergyEntities = allergyEntities;
    }

    public List<MemberCheckEntity> getCheckEntities() {
        return checkEntities;
    }

    public void setCheckEntities(List<MemberCheckEntity> checkEntities) {
        this.checkEntities = checkEntities;
    }

    public List<MemberChildEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(List<MemberChildEntity> childEntities) {
        this.childEntities = childEntities;
    }

    public MemberEntity getMemberEntity() {
        return memberEntity;
    }

    public void setMemberEntity(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    @Override
    public String toString() {
        return "MemberAddForm{" +
                "drugs=" + drugs +
                ", currentEntities=" + currentEntities +
                ", lifeEntities=" + lifeEntities +
                ", levelEntities=" + levelEntities +
                ", allergyEntities=" + allergyEntities +
                ", checkEntities=" + checkEntities +
                ", childEntities=" + childEntities +
                ", memberEntity=" + memberEntity +
                '}';
    }
}
