package com.nocountry.powerfit.service.abstraction;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.nocountry.powerfit.model.request.PreferenceRequest;

import java.util.List;

public interface PreferenceService {
    Preference createPreference(PreferenceRequest request) throws MPException, MPApiException;


    Preference createPreferenceList(List<PreferenceRequest> request) throws MPException, MPApiException;
}
