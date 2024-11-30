package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.HistoryEntity;

import java.util.List;

public interface HistoryService {
    List<HistoryEntity> getHistory();
}
