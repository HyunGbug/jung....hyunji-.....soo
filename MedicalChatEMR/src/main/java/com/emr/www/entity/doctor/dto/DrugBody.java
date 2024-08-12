package com.emr.www.entity.doctor.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class DrugBody {

	 @XmlElementWrapper(name = "items")
	    @XmlElement(name = "item")
    private List<DrugDTO> items;

    public List<DrugDTO> getItems() {
        return items;
    }

    public void setItems(List<DrugDTO> items) {
        this.items = items;
    }
}
