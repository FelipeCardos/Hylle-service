package com.hylle.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponseDTO {
    private List<Item> items;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String id;
        private VolumeInfo volumeInfo;
        private SaleInfo saleInfo;
        private AccessInfo accessInfo;
        private SearchInfo searchInfo;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private String subtitle;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private String description;
        private List<IndustryIdentifier> industryIdentifiers;
        private String previewLink;
        private String infoLink;
        private String canonicalVolumeLink;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndustryIdentifier {
        private String type;
        private String identifier;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SaleInfo {
        private String country;
        private String saleability;
        private Boolean isEbook;
        private String buyLink;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccessInfo {
        private String country;
        private String viewability;
        private Boolean embeddable;
        private Boolean publicDomain;
        private String textToSpeechPermission;
        private Epub epub;
        private Pdf pdf;
        private String webReaderLink;
        private String accessViewStatus;
        private Boolean quoteSharingAllowed;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Epub {
        private Boolean isAvailable;
        private String acsTokenLink;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pdf {
        private Boolean isAvailable;
        private String acsTokenLink;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchInfo {
        private String textSnippet;
    }
}
