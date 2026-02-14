package com.italo.copiavideo.service;

import com.italo.copiavideo.DTO.request.CreateIdeaDTO;
import com.italo.copiavideo.DTO.request.UpdateIdeaDTO;
import com.italo.copiavideo.exceptions.ResourceAlreadyExitsException;
import com.italo.copiavideo.exceptions.ResourceNotFoundException;
import com.italo.copiavideo.infra.internal.GeminiApi;
import com.italo.copiavideo.infra.internal.TranscriptionApi;
import com.italo.copiavideo.infra.internal.ports.IaAPI;
import com.italo.copiavideo.model.Idea;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private TranscriptionApi transcriptionApi;

    private IaAPI iaAPI;

    public IdeaService(GeminiApi geminiApi) {
        this.iaAPI = geminiApi;
    }

    public List<Idea> getAllIdeas(){
        return this.ideaRepository.findAll();
    }

    public List<Idea> getMyIdeas(User user){
        return this.ideaRepository.findByUser_id(user.getId());
    }

    public Idea createIdea(CreateIdeaDTO request, User user) {
        if(this.ideaRepository.existsByTitleAndUserId(request.title(), user.getId())) throw  new ResourceAlreadyExitsException("ideia", "Títiulo");

        Idea ideaModel = new Idea(user, request.video_id(), request.annotations(), request.title());

        return  this.ideaRepository.save(ideaModel);

    }

    public Idea updateIdea(UUID id, UpdateIdeaDTO request, User user){
        Idea idea = this.ideaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ideia", id.toString()));

        if (!idea.getUser().getEmail().equals(user.getEmail())) throw new ResourceNotFoundException("ideia", id.toString());


        idea.setTitle(request.title());
        idea.setAnnotations(request.annotations());

        return this.ideaRepository.save(idea);
    }

    public void deleteIdea(String id, User user){
        Idea idea = this.ideaRepository.findById(UUID.fromString(id)).orElseThrow(()->new ResourceNotFoundException("ideia", id.toString()));

        if (!idea.getUser().getEmail().equals(user.getEmail())) throw new ResourceNotFoundException("ideia", id.toString());

        this.ideaRepository.deleteById(UUID.fromString(id));
    }

    public Idea getIdeaById(String id, User user){

        Idea idea = this.ideaRepository.findById(UUID.fromString(id)).orElseThrow(()->new ResourceNotFoundException("ideia", id.toString()));
        if(!idea.getUser().getId().equals(user.getId())){
            throw  new ResourceNotFoundException("ideia", idea.getId().toString());
        }
        return idea;
    }

    public String generateRoadMapByVideoId(String id){
        Idea idea = this.ideaRepository.findById(UUID.fromString(id)).orElseThrow(()-> new ResourceNotFoundException("ideia", id));
        //String transcript = this.transcriptionApi.getTranscriptVideoById(idea.getVideo_id());

        //o ideal seria enviar o transcript, mas vai estourar o limite da ia

        String prompt = generatePrompt(idea.getTitle(), idea.getAnnotations());

        return this.iaAPI.getResponse(prompt);

    }

    private String generatePrompt(String ideaTitle, String IdeaAnnotations){
        return  "Quero que você atue como um criador profissional de roteiros virais para YouTube.\n" +
                "\n" +
                "Objetivo: Criar um roteiro de um vídeo altamente envolvente e com potencial viral.\n" +
                "\n" +
                "Título base: " + ideaTitle +
                "Anotações importantes: "+ IdeaAnnotations +
                "\n" +
                "Crie um roteiro completo contendo:\n" +
                "\n" +
                "1. Hook extremamente forte nos primeiros 15 segundos\n" +
                "2. Promessa clara do que o público vai ganhar assistindo\n" +
                "3. Estrutura dividida em blocos estratégicos\n" +
                "4. Momentos de quebra de padrão para manter retenção\n" +
                "5. Storytelling envolvente\n" +
                "6. Frases curtas e impactantes\n" +
                "7. CTA final estratégico (inscrição / comentário / like)\n" +
                "\n" +
                "O vídeo deve ter entre 7 e 15 minutos.\n" +
                "Tom: entretenimento\n" +
                "Público-alvo: em geral\n" +
                "\n" +
                "Formate como roteiro estruturado por tópicos e não falas narradas.\n";
    }
}
