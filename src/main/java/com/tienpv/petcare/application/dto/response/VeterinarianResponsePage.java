package com.tienpv.petcare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VeterinarianResponsePage {

    private int page;
    private int totalPage;
    private List<VeterinarianResponse> listResult = new ArrayList<>();
}
