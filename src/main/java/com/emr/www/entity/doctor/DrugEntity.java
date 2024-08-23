package com.emr.www.entity.doctor;

import com.emr.www.entity.patient.MedicalRecordEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Drugs")
public class DrugEntity {

   // 필요한 생성자 추가
   public DrugEntity(String cpntCd, String ingdNameKor, String fomlNm, String dosageRouteCode, String dayMaxDosgQyUnit, String dayMaxDosgQy) {
      this.cpntCd = cpntCd;
      this.ingdNameKor = ingdNameKor;
      this.fomlNm = fomlNm;
      this.dosageRouteCode = dosageRouteCode;
      this.dayMaxDosgQyUnit = dayMaxDosgQyUnit;
      this.dayMaxDosgQy = dayMaxDosgQy;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int no;

   @ManyToOne
   @JoinColumn(name = "chartNum", referencedColumnName = "chartNum")
   private MedicalRecordEntity medicalRecord;

   private String cpntCd;
   private String ingdNameKor;
   private String fomlNm;
   private String dosageRouteCode;
   private String dayMaxDosgQyUnit;
   private String dayMaxDosgQy;
}
