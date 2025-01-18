package com.funnelsensai.core.web.request;

import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListPagesDto;
import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelListResponseDto.FunnelResponseDto;
import com.funnelsensai.core.dto.funnels.listOfFunnels.FunnelPagesCountDto;
import com.funnelsensai.core.service.FunnelGoHighLevelApiServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/funnel")
public class FunnelController {

    private final FunnelGoHighLevelApiServices funnelGoHighLevelApiServices;

    public FunnelController(FunnelGoHighLevelApiServices funnelGoHighLevelApiServices) {
        this.funnelGoHighLevelApiServices = funnelGoHighLevelApiServices;
    }

    @GetMapping("/list")
    public FunnelResponseDto getFunnelList() throws IOException {
        return funnelGoHighLevelApiServices.fetchFunnelsList();
    }
    @GetMapping("/list/pages")
    public FunnelListPagesDto getFunnelListPages() throws IOException {
        return funnelGoHighLevelApiServices.fetchFunnelsListPages();
        }
    @GetMapping("/list/pages/count")
    public FunnelPagesCountDto getFunnelPagesCount() throws IOException {
        return funnelGoHighLevelApiServices.fetchFunnelPagesCount();
    }
}
