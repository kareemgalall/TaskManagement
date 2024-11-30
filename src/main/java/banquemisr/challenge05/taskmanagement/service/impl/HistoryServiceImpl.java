package banquemisr.challenge05.taskmanagement.service.impl;

import banquemisr.challenge05.taskmanagement.domain.model.HistoryEntity;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.repository.HistoryRepository;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.HistoryService;
import banquemisr.challenge05.taskmanagement.service.UserUtilityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final UserUtilityService userUtilityService;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(UserUtilityService userUtilityService, UserRepository userRepository, HistoryRepository historyRepository) {
        this.userUtilityService = userUtilityService;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }
    @Override
    public List<HistoryEntity> getHistory() {
        Long id=userUtilityService.getAuthenticatedUserId();
        UserEntity userEntity=userRepository.findById(id).get();
        return historyRepository.findByUser(userEntity);
    }
    @Transactional
    @Override
    public void clearHistory() {
        Long id=userUtilityService.getAuthenticatedUserId();
        historyRepository.deleteByUserId(id);
    }
}
