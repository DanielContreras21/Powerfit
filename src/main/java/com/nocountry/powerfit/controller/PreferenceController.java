package com.nocountry.powerfit.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.nocountry.powerfit.model.exception.ResourceNotFoundException;
import com.nocountry.powerfit.model.request.PreferenceRequest;
import com.nocountry.powerfit.model.response.ProductResponse;
import com.nocountry.powerfit.model.response.TransactionDataResponse;
import com.nocountry.powerfit.model.response.UserResponse;
import com.nocountry.powerfit.service.abstraction.IProductService;
import com.nocountry.powerfit.service.abstraction.PreferenceService;
import com.nocountry.powerfit.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/preferences")
@CrossOrigin(origins = "*")
public class PreferenceController {

    private final PreferenceService preferenceService;
    private final UserService userService;
    private final IProductService productService;
    @PostMapping("/create")
    public ResponseEntity<Preference> createPreference(@RequestBody PreferenceRequest request) throws MPException, MPApiException {
        Preference response = preferenceService.createPreference(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/createList")
    public ResponseEntity<Preference> createPreferenceList(@RequestBody List<PreferenceRequest> request) throws MPException, MPApiException {
        Preference response = preferenceService.createPreferenceList(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/notification")
    public ResponseEntity<String> handleWebhookNotification(@RequestBody String notification) {
        // Aquí puedes procesar la notificación recibida de Mercado Pago

        // Ejemplo: Imprimir la notificación en la consola
        System.out.println("Notificación recibida: " + notification.toString());

        // Puedes realizar cualquier acción adicional según tus necesidades

        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/payment-response", method = RequestMethod.GET)
    public TransactionDataResponse handlePaymentResponse(@RequestParam String collection_id,
                                                         @RequestParam String collection_status,
                                                         @RequestParam String payment_id,
                                                         @RequestParam String status,
                                                         @RequestParam String external_reference,
                                                         @RequestParam String payment_type,
                                                         @RequestParam String merchant_order_id,
                                                         @RequestParam String preference_id,
                                                         @RequestParam String site_id,
                                                         @RequestParam String processing_mode,
                                                         @RequestParam String merchant_account_id) throws ResourceNotFoundException {

        // Construir la respuesta con los datos de la transacción
        TransactionDataResponse response = TransactionDataResponse.builder()
                .collection_id(collection_id)
                .collection_status(collection_status)
                .payment_id(payment_id)
                .status(status)
                .external_reference(external_reference)
                .payment_type(payment_type)
                .merchant_order_id(merchant_order_id)
                .preference_id(preference_id)
                .site_id(site_id)
                .processing_mode(processing_mode)
                .merchant_account_id(merchant_account_id)
                .build();

        // Devolver la respuesta en formato JSON
        return response;
    }
}
