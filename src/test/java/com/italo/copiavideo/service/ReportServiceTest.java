package com.italo.copiavideo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @InjectMocks
    ReportService reportService;


    @Test
    void saveTheSearch() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("mod");
        result.add("vingadores");
        result.add("minecraft");
        assertEquals(this.reportService.saveSearch("mod de vingadores no minecraft"), result);
    }
}