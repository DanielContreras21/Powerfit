package com.nocountry.powerfit.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.nocountry.powerfit.model.request.PreferenceRequest;
import com.nocountry.powerfit.model.response.UserResponse;
import com.nocountry.powerfit.service.abstraction.PreferenceService;
import com.nocountry.powerfit.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PreferenceServiceImpl implements PreferenceService {

    private final UserService userService;
    @Value("${spring.mercado.pago.access.token}")
    String mercadoPagoAccessToken;


    @Override
    public Preference createPreference(PreferenceRequest request) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        // Crea un objeto de preferencia
        PreferenceClient client = new PreferenceClient();
        // crea un item en la preferencia

        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .id(String.valueOf(request.getId()))
                .title(request.getName())
                .description(request.getDescription())
                .unitPrice(request.getPrice())
                .currencyId("ARS")
                .quantity(request.getQuantity())
                .pictureUrl(request.getPictureUrl())
                .build();
        items.add(item);

       // UserResponse user = userService.getUserInfo();

        // Agrego informacion del usuario
        PreferencePayerRequest payerRequest = PreferencePayerRequest.builder()

                .name("Abel")
                .email("marzoa3581@gmail.com")

                .address(AddressRequest.builder()
                        .streetName("Garin")
                        .streetNumber("18")
                        .zipCode("1619")
                        .build())
                .build();
       // UserResponse user = userService.getUserInfo();
        //Url de respuestas
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://power-fit-v3.netlify.app/payment/success") // <-- Aquí se cambia la URL de éxito a la URL de respuesta genérica
                .failure("https://power-fit-v3.netlify.app/payment/failure") // <-- También se cambia la URL de fallo a la URL de respuesta genérica
                .pending("https://power-fit-v3.netlify.app/payment/pending") // <-- También se cambia la URL de pendiente a la URL de respuesta genérica
                .build();

       // String notification = "https://a604-2800-810-50c-4cc-98f9-67c2-6b92-4531.ngrok.io/api/v1/preferences/notification";

        com.mercadopago.client.preference.PreferenceRequest preferenceRequest = com.mercadopago.client.preference.PreferenceRequest.builder()
                .items(items)
                .payer(payerRequest)
                .backUrls(backUrls)
               // .notificationUrl(notification)
                .binaryMode(true)
                .autoReturn("approved")
                .build();

        Preference response = client.create(preferenceRequest);

        return response;
    }

    @Override
    public Preference createPreferenceList(List<PreferenceRequest> requestList) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        // Crea un objeto de preferencia
        PreferenceClient client = new PreferenceClient();
        // crea un item en la preferencia

        List<PreferenceItemRequest> items = new ArrayList<>();

        for(PreferenceRequest request: requestList) {
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .id(String.valueOf(request.getId()))
                    .title(request.getName())
                    .description(request.getDescription())
                    .unitPrice(request.getPrice())
                    .currencyId("ARS")
                    .quantity(request.getQuantity())
                    .pictureUrl(request.getPictureUrl())
                    .build();
            items.add(item);
        }
        // Agrego informacion del usuario
        PreferencePayerRequest payerRequest = PreferencePayerRequest.builder()

                .name("Abel")
                .email("marzoa3581@gmail.com")

                .address(AddressRequest.builder()
                        .streetName("Garin")
                        .streetNumber("18")
                        .zipCode("1619")
                        .build())
                .build();
        // UserResponse user = userService.getUserInfo();
        //Url de respuestas
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://power-fit-v3.netlify.app/payment/success") // <-- Aquí se cambia la URL de éxito a la URL de respuesta genérica
                .failure("https://power-fit-v3.netlify.app/payment/failure") // <-- También se cambia la URL de fallo a la URL de respuesta genérica
                .pending("https://power-fit-v3.netlify.app/payment/pending") // <-- También se cambia la URL de pendiente a la URL de respuesta genérica
                .build();

        // String notification = "https://a604-2800-810-50c-4cc-98f9-67c2-6b92-4531.ngrok.io/api/v1/preferences/notification";

        com.mercadopago.client.preference.PreferenceRequest preferenceRequest = com.mercadopago.client.preference.PreferenceRequest.builder()
                .items(items)
                .payer(payerRequest)
                .backUrls(backUrls)
                // .notificationUrl(notification)
                .binaryMode(true)
                .autoReturn("approved")
                .build();

        Preference response = client.create(preferenceRequest);

        return response;
    }



}
