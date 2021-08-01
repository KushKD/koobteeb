package com.hp.dit.beetbook.repositories.information;

import com.hp.dit.beetbook.entities.InformationEntity;
import com.hp.dit.beetbook.modals.information.InformationMarkers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepositoryCustom {

    //getPushPinMarkersGpsModuleIdSumboduleId
    List<InformationMarkers> getmarkers(Integer moduleId, Integer submoduleId);
    List<InformationMarkers> getmarkersLocationWise(Integer moduleId, Integer submoduleId, Double latitude, Double longitude);
    List<InformationMarkers> getmarkersViaLocation(Integer moduleId, Integer submoduleId, Integer beatId, Integer psId);

    //getInformationViaId
    InformationEntity getInformationViaId(Integer id);
    InformationEntity getInformationViaId(Integer id,Integer moduleId, Integer SubmoduleId);




}
