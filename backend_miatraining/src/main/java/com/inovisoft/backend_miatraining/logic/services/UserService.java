package com.inovisoft.backend_miatraining.logic.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.UserNotFoundException;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.TrainingTypeDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.TrainingTypeDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.*;
import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.mappers.ProfileInfoResponseDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.mappers.UserDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.userDTO.mappers.UserPageResponseDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.*;
import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.mappers.*;
import com.inovisoft.backend_miatraining.models.RoleModel;
import com.inovisoft.backend_miatraining.models.UserModel;
import com.inovisoft.backend_miatraining.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    IRoleRepo roleRepo;
    @Autowired
    UserDTOMapper userDTOMapper;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserPageResponseDTOMapper userPageResponseDTOMapper;
    @Autowired
    ProfileInfoResponseDTOMapper profileInfoResponseDTOMapper;
    @Autowired
    IDietTypeRepo dietTypeRepo;
    @Autowired
    ITrainingTypeRepo trainingTypeRepo;
    @Autowired
    ITrainingExperienceRepo trainingExperienceRepo;
    @Autowired
    IObjectiveRepo objectiveRepo;
    @Autowired
    IBodyTypeRepo bodyTypeRepo;
    @Autowired
    BodyTypeDTOMapper bodyTypeDTOMapper;
    @Autowired
    DietTypeDTOMapper dietTypeDTOMapper;
    @Autowired
    ObjectiveDTOMapper objectiveDTOMapper;
    @Autowired
    TrainingTypeDTOMapper trainingTypeDTOMapper;
    @Autowired
    TrainingExperienceDTOMapper trainingExperienceDTOMapper;

    public void toggleEnabled(String email){
        UserModel userModel = userRepo.findByEmailIgnoreCase(email)
                .orElseThrow(UserNotFoundException::new);
        userModel.setEnabled(!userModel.getEnabled());
        userRepo.save(userModel);
    }

    public ArrayList<RoleDTO> getRoles(){
        ArrayList<RoleModel> roleModels = roleRepo.getAllRoles();
        return  roleModels.stream().map(
                (role)-> RoleDTO.builder().id(role.getRoleID()).role(role.getRoleName()).build())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public UserModel getUserById(Long id){
        return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserDTO getUserDTOById(Long id){
        UserModel userModel = userRepo.findById(id).orElseThrow(UserNotFoundException::new);
        return userDTOMapper.apply(userModel);
    }
    public ProfileInfoResponseDTO getProfileInfoByEmail(String email){
        UserModel userModel = userRepo.findByEmailIgnoreCase(email).orElseThrow(UserNotFoundException::new);
        return profileInfoResponseDTOMapper.apply(userModel);
    }

    public void saveProfileInfo(String email, ProfileInfoUpdateDTO updateDTO){
        authenticationService.findExistingUser(email);
        UserModel userModel = userRepo.findByEmailIgnoreCase(email).orElseThrow(UserNotFoundException::new);
        userModel.setEmail(userModel.getEmail());
        userModel.setPictureUrlString(updateDTO.getPictureUrlString());
        userRepo.save(userModel);
    }

    public UserDTO getUserDTOByEmail(String email){
        UserModel userModel = userRepo.findByEmailIgnoreCase(email).orElseThrow(UserNotFoundException::new);
        return userDTOMapper.apply(userModel);
    }

    public ArrayList<UserDTO> getUsers(){
        ArrayList<UserModel> models = userRepo.findAllUsers();
        ArrayList<UserDTO> dtos = new ArrayList<>();
        models.forEach(model->{
            dtos.add(userDTOMapper.apply(model));
        });
        return dtos;
    }

    public UserPageResponseDTO getUsersByPage(int pageNumber, int pageSize,String search, String role) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Manejar caso en que search o role son nulos o vacíos
        String searchTerm = (search == null || search.trim().isEmpty()) ? "" : search.trim();
        String roleFilter = (role == null || role.trim().isEmpty()) ? "" : role.trim();

        // Realizar la búsqueda con los filtros y la paginación
        Page<UserModel> userPage = userRepo.findUsersByEmailOrNameOrLastNameAndRole(searchTerm, roleFilter, pageable);

        // Usar Page.map() para convertir de UserModel a UserDTO
        Page<UserDTO> dtoPage = userPage.map(userDTOMapper);

        // Transformar el Page<UserDTO> en la respuesta UserPageResponseDTO
        return userPageResponseDTOMapper.apply(dtoPage);
    }

    public UserDetailsFormDTO getUserDetailsOptions() {
        List<ObjectiveDTO> objectiveDTOS = objectiveRepo.findAll().stream().map(objectiveDTOMapper).toList();
        List<DietTypeDTO> dietTypeDTOS = dietTypeRepo.findAll().stream().map(dietTypeDTOMapper).toList();
        List<BodyTypeDTO> bodyTypeDTOS = bodyTypeRepo.findAll().stream().map(bodyTypeDTOMapper).toList();
        List<TrainingExperienceDTO> trainingExperienceDTOS =
                trainingExperienceRepo.findAll().stream().map(trainingExperienceDTOMapper).toList();
        List<TrainingTypeDTO> trainingTypeDTOS =
                trainingTypeRepo.findAll().stream().map(trainingTypeDTOMapper).toList();

        return UserDetailsFormDTO
                .builder()
                .bodyType(bodyTypeDTOS)
                .diet(dietTypeDTOS)
                .trainingType(trainingTypeDTOS)
                .experience(trainingExperienceDTOS)
                .objective(objectiveDTOS)
                .build();
    }
}

