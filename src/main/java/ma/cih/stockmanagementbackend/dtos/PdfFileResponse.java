package ma.cih.stockmanagementbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PdfFileResponse {
    private String name;
    private String url;
    private long size;
}