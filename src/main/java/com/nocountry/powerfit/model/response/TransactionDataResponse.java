package com.nocountry.powerfit.model.response;

import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class TransactionDataResponse {

   private String collection_id;
   private String collection_status;
   private String payment_id;
   private String status;
   private String external_reference;
   private String payment_type;
   private String merchant_order_id;
   private String preference_id;
   private String site_id;
   private String processing_mode;
   private String merchant_account_id;
   private String name;
   private String email;
   private Long productId;
}
