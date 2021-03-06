package com.mss1569.cartoriofront.controller;

import com.mss1569.cartoriofront.dto.CertificateDTO;
import com.mss1569.cartoriofront.dto.NotaryDTO;
import com.mss1569.cartoriofront.service.NotaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class NotaryController {
    @Autowired
    private NotaryService notaryService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping({"/notaries/list", "/"})
    public String listAll(Model model,
                          Pageable pageable) {
        model.addAttribute("notaries", notaryService.listAll(pageable));
        return "index";
    }

    @GetMapping("/notaries/show/{notaryId}")
    public String showNotary(@PathVariable Long notaryId,
                             Model model,
                             Pageable pageable) {
        model.addAttribute("notary", notaryService.findById(notaryId));
        model.addAttribute("certificates", notaryService.listAllCertificatesByNotary(notaryId, pageable));
        return "detail-notary";
    }

    @GetMapping("/notaries/save")
    public String showSaveForm(Model model) {
        NotaryDTO notaryDTO = NotaryDTO.builder()
                .build();

        model.addAttribute("notaryDTO", notaryDTO);
        return "save-notary";
    }

    @PostMapping("/notaries/save")
    public String save(@Valid @ModelAttribute(value = "notaryDTO") NotaryDTO notaryDTO,
                       BindingResult errors,
                       Model model) {
        if (errors.hasErrors())
            return "save-notary";

        notaryService.save(notaryDTO);
        return "redirect:/";
    }

    @GetMapping("/notaries/edit/{notaryId}")
    public String showUpdateForm(@PathVariable Long notaryId,
                                 Model model) {
        model.addAttribute("notaryId", notaryId);
        model.addAttribute("notaryDTO", modelMapper.map(notaryService.findById(notaryId), NotaryDTO.class));
        return "edit-notary";
    }

    @PostMapping("/notaries/edit/{notaryId}")
    public String update(@PathVariable Long notaryId,
                         @Valid @ModelAttribute(value = "notaryDTO") NotaryDTO notaryDTO,
                         BindingResult errors,
                         Model model) {
        if (errors.hasErrors())
            return "edit-notary";

        notaryService.update(notaryId,notaryDTO);
        return "redirect:/";
    }

    @GetMapping("/notaries/delete/{notaryId}")
    public String delete(@PathVariable Long notaryId,
                         Model model) {
        notaryService.delete(notaryId);
        return "redirect:/";
    }

    @GetMapping("/notaries/{notaryId}/certificates/save")
    public String showSaveCertificateForm(@PathVariable Long notaryId,
                                          Model model) {
        CertificateDTO certificateDTO = CertificateDTO.builder()
                .build();

        model.addAttribute("certificateDTO", certificateDTO);
        model.addAttribute("notaryId", notaryId);
        return "save-certificate";
    }

    @PostMapping("/notaries/{notaryId}/certificates/save")
    public String saveCertificate(@PathVariable Long notaryId,
                                  @Valid @ModelAttribute(value = "certificateDTO") CertificateDTO certificateDTO,
                                  BindingResult errors,
                                  Model model) {
        if (errors.hasErrors())
            return "save-certificate";

        notaryService.saveCertificate(notaryId, certificateDTO);
        return "redirect:/notaries/show/" + notaryId;
    }

    @GetMapping("/notaries/{notaryId}/certificates/edit/{certificateId}")
    public String showUpdateCertificateForm(@PathVariable Long notaryId,
                                            @PathVariable Long certificateId,
                                            Model model) {
        model.addAttribute("certificateId", certificateId);
        model.addAttribute("notaryId", notaryId);
        model.addAttribute("certificateDTO", modelMapper.map(notaryService.findCertificateById(certificateId), CertificateDTO.class));
        return "edit-certificate";
    }

    @PostMapping("/notaries/{notaryId}/certificates/edit/{certificateId}")
    public String updateCertificate(@PathVariable Long notaryId,
                                    @PathVariable Long certificateId,
                                    @Valid @ModelAttribute(value = "certificateDTO") CertificateDTO certificateDTO,
                                    BindingResult errors,
                                    Model model) {
        if (errors.hasErrors())
            return "edit-certificate";

        notaryService.updateCertificate(certificateId,certificateDTO);
        return "redirect:/notaries/show/" + notaryId;
    }

    @GetMapping("/notaries/{notaryId}/certificates/delete/{certificateId}")
    public String deleteCertificate(@PathVariable Long notaryId,
                                    @PathVariable Long certificateId,
                                    Model model) {
        notaryService.deleteCertificate(certificateId);
        return "redirect:/notaries/show/" + notaryId;
    }
}
