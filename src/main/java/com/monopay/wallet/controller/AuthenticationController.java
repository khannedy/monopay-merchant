package com.monopay.wallet.controller;

import com.monopay.wallet.entity.Authentication;
import com.monopay.wallet.entity.Merchant;
import com.monopay.wallet.model.web.WebResponse;
import com.monopay.wallet.model.web.response.MerchantResponse;
import com.monopay.wallet.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/auth")
@RestController
public class AuthenticationController {

  @Autowired
  private MerchantRepository merchantRepository;

  @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<MerchantResponse> authenticate(Authentication authentication) {
    Merchant merchant = merchantRepository.findById(authentication.getId()).get();

    MerchantResponse response = MerchantResponse.builder()
      .id(merchant.getId())
      .name(merchant.getName())
      .build();

    return WebResponse.<MerchantResponse>builder()
      .code(HttpStatus.OK.value())
      .status(HttpStatus.OK.name())
      .data(response)
      .build();
  }

}
