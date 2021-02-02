package com.sunsharing.groupid.demo.util;

import java.util.List;
import lombok.Data;

@Data
public class One2ManyUpdateDataHolder<AddObject, UpdateObject, PrimaryType> {

    private List<AddObject> add;
    private List<UpdateObject> update;
    private List<PrimaryType> delete;

}
