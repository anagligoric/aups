package com.example.aups.services;

import com.example.aups.exceptions.CustomException;
import com.example.aups.exceptions.UserDoesNotExistException;
import com.example.aups.exceptions.UserWithEmailDoesNotExistException;
import com.example.aups.models.Team;
import com.example.aups.models.User;
import com.example.aups.repositories.TeamRepository;
import com.example.aups.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional(readOnly = true)
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new CustomException("Team with id " + id + " cannot be found"));
    }

    @Transactional
    public Team create(Team team) {
        if (areTeamMembersValid(team.getTechnicians())) {
            return teamRepository.save(team);
        }
        throw new CustomException("User with role technician cannot be added to the team");
    }

    @Transactional
    public Team update(Long id, Team team) {

        if (teamRepository.findById(id).isEmpty()) {
            throw new CustomException("Team wit id " + id + " cannot be found");
        }
        if (areTeamMembersValid(team.getTechnicians())) {
            return teamRepository.save(team);
        }
        throw new CustomException("User with role technician cannot be added to the team");
    }

    @Transactional
    public void delete(Long id) {
        if (teamRepository.findById(id).isEmpty()) {
            throw new CustomException("Team wit id " + id + " cannot be found");
        }
        teamRepository.deleteById(id);
    }

    private boolean areTeamMembersValid(Set<User> technicians) {
        Set<String> roleNames = technicians.stream().map(technician -> technician.getRole().getName()).collect(Collectors.toSet());
        return roleNames.contains("ROLE_ADMIN");
    }
}

