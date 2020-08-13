package ru.geekbrains.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.model.Brand;
import ru.geekbrains.repo.BrandRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
@WithMockUser(value = "admin", password = "admin", roles = "ADMIN")
@TestPropertySource(locations = "classpath:application-test.properties")
public class BrandControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void FindAllBrandsTest() throws Exception {
        mockMvc.perform(get("/brands"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("brands", brandRepository.findAll()))
                .andExpect(view().name("brands"));
    }

    @Test
    public void createBrandTest() throws Exception {
        mockMvc.perform(get("/brand/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("brand"))
                .andExpect(view().name("brand_form"));
    }

    @Test
    public void editBrandTest() throws Exception {
        mockMvc.perform(get("/brand/3/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("brand",brandRepository.findById(3L)))
                .andExpect(view().name("brand_form"));
    }

    @Test
    public void deleteBrandTest() throws Exception {
        mockMvc.perform(delete("/brand/1/delete")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));
    }

    @Test
    public void newBrandTest() throws Exception {
        mockMvc.perform(post("/brand")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","-1")
                .param("name", "New Brand")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));

        Brand brand = new Brand();
        brand.setName("New Brand");
        Optional<Brand> actualBrand = brandRepository.findOne(Example.of(brand));
        assertTrue(actualBrand.isPresent());
        assertEquals("New Brand", actualBrand.get().getName());
    }
}