package com.malu.base.gift.consumer.service.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListHashCodeDTO implements Serializable {

    @NotNull
    private List<String> listHashCode = new ArrayList<>();
}
