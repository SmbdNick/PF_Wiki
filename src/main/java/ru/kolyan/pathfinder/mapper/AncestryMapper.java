package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryLanguageRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryTraitsRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.CreateAncestryRequest;
import ru.kolyan.pathfinder.controller.ancestry.response.GetAllAncestryResponse;
import ru.kolyan.pathfinder.controller.ancestry.response.GetByIdAncestryResponse;
import ru.kolyan.pathfinder.model.Ancestry;
import ru.kolyan.pathfinder.model.AncestryLanguage;
import ru.kolyan.pathfinder.model.AncestryTrait;

import java.util.ArrayList;
import java.util.List;

@Component
public class AncestryMapper {
    public Ancestry fromCreateDto(CreateAncestryRequest request) {
        Ancestry ancestry = new Ancestry();
        ancestry.setAttributeComboId(request.getAttributeComboId());
        ancestry.setDescription(request.getDescription());
        ancestry.setHp(request.getHp());
        ancestry.setName(request.getName());
        ancestry.setSize(request.getSize());
        ancestry.setSpeed(request.getSpeed());

        return ancestry;
    }

    public GetByIdAncestryResponse toGetByIdDto(Ancestry ancestry, String comboName, List<String> traits, List<String> languages) {
        return GetByIdAncestryResponse.builder()
                .id(ancestry.getId())
                .name(ancestry.getName())
                .hp(ancestry.getHp())
                .size(ancestry.getSize())
                .speed(ancestry.getSpeed())
                .attributeComboId(ancestry.getAttributeComboId())
                .comboName(comboName)
                .description(ancestry.getDescription())
                .traitList(traits)
                .languageList(languages)
                .build();
    }

    public GetAllAncestryResponse.Ancestry toGetAllContentDto(Ancestry ancestry, String comboName) {
        return GetAllAncestryResponse.Ancestry.builder()
                .id(ancestry.getId())
                .name(ancestry.getName())
                .hp(ancestry.getHp())
                .size(ancestry.getSize())
                .speed(ancestry.getSpeed())
                .attributeComboId(ancestry.getAttributeComboId())
                .comboName(comboName)
                .description(ancestry.getDescription())
                .build();
    }

    public List<AncestryTrait> fromAddTraitsDto(AddAncestryTraitsRequest request, Ancestry ancestry) {
        List<AncestryTrait> ancestryTraitList = new ArrayList<>();

        request.getTraitIdList().forEach(traitId -> {
            AncestryTrait ancestryTrait = new AncestryTrait();
            ancestryTrait.setTraitId(traitId);
            ancestryTrait.setAncestryId(ancestry.getId());

            ancestryTraitList.add(ancestryTrait);
        });

        return ancestryTraitList;
    }

    public List<AncestryLanguage> fromAddLanguagesDto(AddAncestryLanguageRequest request, Ancestry ancestry) {
        List<AncestryLanguage> ancestryLanguageList = new ArrayList<>();

        request.getLanguageIdList().forEach(languageId -> {
            AncestryLanguage ancestryLanguage = new AncestryLanguage();
            ancestryLanguage.setLanguageId(languageId);
            ancestryLanguage.setAncestryId(ancestry.getId());

            ancestryLanguageList.add(ancestryLanguage);
        });

        return ancestryLanguageList;
    }
}
