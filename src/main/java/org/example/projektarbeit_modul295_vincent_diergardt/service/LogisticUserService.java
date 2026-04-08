package org.example.projektarbeit_modul295_vincent_diergardt.service;

import org.example.projektarbeit_modul295_vincent_diergardt.dto.LogisticUserDTO;
import org.example.projektarbeit_modul295_vincent_diergardt.model.LogisticUser;
import org.example.projektarbeit_modul295_vincent_diergardt.repository.LogisticUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogisticUserService {

    private final LogisticUserRepository logisticUserRepository;

    public LogisticUserService(LogisticUserRepository logisticUserRepository) {
        this.logisticUserRepository = logisticUserRepository;
    }

    public List<LogisticUserDTO> getAllLogisticUsers() {
        return logisticUserRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LogisticUserDTO createLogisticUser(LogisticUserDTO logisticUserDTO) {
        LogisticUser logisticUser = convertToEntity(logisticUserDTO);
        logisticUser.setCreateDate(LocalDateTime.now());
        LogisticUser savedLogisticUser = logisticUserRepository.save(logisticUser);
        return convertToDTO(savedLogisticUser);
    }

    public void deleteLogisticUser(Long id) {
        logisticUserRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return logisticUserRepository.existsById(id);
    }

    private LogisticUserDTO convertToDTO(LogisticUser logisticUser) {
        LogisticUserDTO dto = new LogisticUserDTO();
        dto.setId(logisticUser.getId());
        dto.setFirstname(logisticUser.getFirstname());
        dto.setLastname(logisticUser.getLastname());
        dto.setEmail(logisticUser.getEmail());
        dto.setCreateDate(logisticUser.getCreateDate());
        return dto;
    }

    private LogisticUser convertToEntity(LogisticUserDTO dto) {
        LogisticUser logisticUser = new LogisticUser();
        logisticUser.setFirstname(dto.getFirstname());
        logisticUser.setLastname(dto.getLastname());
        logisticUser.setEmail(dto.getEmail());
        return logisticUser;
    }
}
