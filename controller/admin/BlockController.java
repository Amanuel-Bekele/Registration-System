package com.waaproject.registrationsystem.controller.admin;

import com.waaproject.registrationsystem.domain.Block;
import com.waaproject.registrationsystem.domain.Student;
import com.waaproject.registrationsystem.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/blocks")
public class BlockController {

    private BlockService blockService;

    @Autowired
    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @GetMapping(value = {"","/","/index","/list"})
    public String index(Model model,@SortDefault("year") Pageable pageable) {

        Page<Block> paginatedBlocks = blockService.getAllPaginated(pageable);
        model.addAttribute("pBlocks", paginatedBlocks);

        return "admin/block/list";
    }


    @GetMapping(value = {"/create","/add"})
    public String create(@ModelAttribute("block") Block block, Model model){

        return "admin/block/create";
    }


    @PostMapping(value = "/save")
    public String save(@ModelAttribute Block block, BindingResult result, Model model, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "admin/block/create";
        }

        blockService.save(block);
        redirectAttributes.addFlashAttribute("successMessage","Block added successfully.");

        return "redirect:/admin/blocks/list";
    }
}
