package com.programming.techie.inventoryserive.controller;

import com.programming.techie.inventoryserive.dto.InventoryResponse;
import com.programming.techie.inventoryserive.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        try {
            return inventoryService.isInStock(skuCode);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
