package banquemisr.challenge05.taskmanagement.mapper.impl;

import banquemisr.challenge05.taskmanagement.mapper.Mapper;
import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements Mapper<TaskEntity, TaskDto> {
    private ModelMapper modelMapper;
    public TaskMapper(ModelMapper modelMapper) {}
    @Override
    public TaskEntity mapTo(TaskDto taskDto) {
        return modelMapper.map(taskDto, TaskEntity.class);
    }

    @Override
    public TaskDto mapFrom(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity, TaskDto.class);
    }
}
