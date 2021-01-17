package com.mss1569.cartoriofront.controller;

import com.mss1569.cartoriofront.domain.Certificate;
import com.mss1569.cartoriofront.domain.Notary;
import com.mss1569.cartoriofront.dto.CertificateDTO;
import com.mss1569.cartoriofront.dto.NotaryDTO;
import com.mss1569.cartoriofront.service.NotaryService;
import lombok.extern.log4j.Log4j2;
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

@Controller
@Log4j2
public class NotaryController {
    @Autowired
    private NotaryService notaryService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping({"/list", "/"})
    public String listAll(Model model,
                          Pageable pageable) {
        model.addAttribute("notaries", notaryService.listAll(pageable));
        return "index";
    }

    @GetMapping("/show/{notaryId}")
    public String showNotary(@PathVariable Long notaryId,
                             Model model,
                             Pageable pageable) {
        model.addAttribute("notary", notaryService.findById(notaryId));
        model.addAttribute("certificates", notaryService.listAllCertificatesByNotary(notaryId, pageable));
        return "detail-notary";
    }

    @GetMapping("/save")
    public String showSaveForm(Model model) {
        NotaryDTO notaryDTO = NotaryDTO.builder()
                .build();

        model.addAttribute("notaryDTO", notaryDTO);
        return "save-notary";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(value = "notaryDTO") NotaryDTO notaryDTO,
                       BindingResult errors,
                       Model model) {
        notaryService.save(notaryDTO);
        return "redirect:/list";
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
                                  @ModelAttribute(value = "certificateDTO") CertificateDTO certificateDTO,
                                  BindingResult errors,
                                  Model model) {
        notaryService.saveCertificate(notaryId, certificateDTO);
        return "redirect:/show/" + notaryId;
    }

    @GetMapping("/edit/{notaryId}")
    public String showUpdateForm(@PathVariable Long notaryId,
                                 Model model) {
        model.addAttribute("notaryId", notaryId);
        model.addAttribute("notaryDTO", modelMapper.map(notaryService.findById(notaryId), NotaryDTO.class));
        return "edit-notary";
    }

    @PostMapping("/edit/{notaryId}")
    public String update(@PathVariable Long notaryId,
                         @ModelAttribute(value = "notaryDTO") NotaryDTO notaryDTO,
                         BindingResult errors,
                         Model model) {
        notaryService.update(notaryId,notaryDTO);
        return "redirect:/list";
    }

    @GetMapping("/certificates/edit/{certificateId}")
    public String showUpdateCertificateForm(@PathVariable Long certificateId,
                                            Model model) {
        model.addAttribute("certificateId", certificateId);
        model.addAttribute("certificateDTO", modelMapper.map(notaryService.findCertificateById(certificateId), CertificateDTO.class));
        return "edit-certificate";
    }

    @PostMapping("/certificates/edit/{certificateId}")
    public String updateCertificate(@PathVariable Long certificateId,
                                    @ModelAttribute(value = "certificateDTO") CertificateDTO certificateDTO,
                                    BindingResult errors,
                                    Model model) {
        notaryService.updateCertificate(certificateId,certificateDTO);
        return "redirect:/list";
    }

    @GetMapping("/delete/{notaryId}")
    public String delete(@PathVariable Long notaryId,
                         Model model) {
        notaryService.delete(notaryId);
        return "redirect:/list";
    }

    @GetMapping("/certificates/delete/{certificateId}")
    public String deleteCertificate(@PathVariable Long certificateId,
                                    Model model) {
        notaryService.deleteCertificate(certificateId);
        return "redirect:/list";
    }
}
