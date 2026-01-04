package com.bit.backend.services.impl;

import com.bit.backend.dtos.WorkoutPlanGenerateDto;
import com.bit.backend.services.WorkoutPlanGenerateServiceI;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class WorkoutPlanGenerateService implements WorkoutPlanGenerateServiceI {

    private final ChatClient chatClient;

    public WorkoutPlanGenerateService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public Flux<String> generateWorkoutPlan(WorkoutPlanGenerateDto request) {
        String prompt = """
            Create a summarised 1-day workout plan for the following user:
            - Age: %d
            - Gender: %s
            - Fitness level: %s (beginner/intermediate/advanced)
            - Goal: %s (e.g., build muscle, lose fat, improve endurance)
            - Available equipment: %s
            - Days per week available: %d
            - Any injuries or limitations: %s
            
            Format the response as structured text with:
            - Daily workouts (warm-up, exercises with sets/reps, rest times).
            - Only generate 4 workouts per day
            - At the end send, please take the plan to a trainer in case of inquiry
            """.formatted(
                request.getAge(),
                request.getGender(),
                request.getExperience(),
                request.getGoal(),
                request.getEquipment(),
                request.getDaysPerWeek(),
                request.getLimitation()
        );

        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }
}
