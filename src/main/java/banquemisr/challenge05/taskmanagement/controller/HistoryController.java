package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.domain.model.HistoryEntity;
import banquemisr.challenge05.taskmanagement.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {
    private final HistoryService historyService;
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }
    @GetMapping("/history")
    public List<HistoryEntity> getHistory() {
        return historyService.getHistory();
    }
}
