package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

@Mapper(componentModel = "spring")
public interface AncestryMapper {
    @Mapping(target = "id", ignore = true)
    Ancestry fromCreateDto(CreateAncestryRequest request);

    @Mapping(source = "traits", target = "traitList")
    @Mapping(source = "languages", target = "languageList")
    GetByIdAncestryResponse toGetByIdDto(Ancestry ancestry, String comboName, List<String> traits, List<String> languages);

    GetAllAncestryResponse.Ancestry toGetAllContentDto(Ancestry ancestry, String comboName);

    default List<AncestryTrait> fromAddTraitsDto(AddAncestryTraitsRequest request, Ancestry ancestry) {
        List<AncestryTrait> ancestryTraitList = new ArrayList<>();

        request.getTraitIdList().forEach(traitId -> {
            AncestryTrait ancestryTrait = new AncestryTrait();
            ancestryTrait.setTraitId(traitId);
            ancestryTrait.setAncestryId(ancestry.getId());

            ancestryTraitList.add(ancestryTrait);
        });

        return ancestryTraitList;
    }

    default List<AncestryLanguage> fromAddLanguagesDto(AddAncestryLanguageRequest request, Ancestry ancestry) {
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
