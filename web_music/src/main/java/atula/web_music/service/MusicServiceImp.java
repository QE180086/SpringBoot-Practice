package atula.web_music.service;

import atula.web_music.dto.MusicFavourtitesDto;
import atula.web_music.enetity.Music;
import atula.web_music.repository.MusicRepository;
import atula.web_music.request.MusicRequest;
import atula.web_music.respone.MusicRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static atula.web_music.utils.CodeAndMessage.*;

@Service
public class MusicServiceImp implements MusicService{
    @Autowired
    private MusicRepository musicRepository;
    @Override
    public MusicRespone loadMusic(MusicRequest musicRequest) {
        Music newMusic = Music.builder()
                .name(musicRequest.getName())
                .type(musicRequest.getType())
                .url(musicRequest.getUrl())
                .auth(musicRequest.getAuth())
                .description(musicRequest.getDescription())
                .build();
        Music savedMusic = musicRepository.save(newMusic);

        return MusicRespone.builder()
                .code(LOAD_MUSIC_CODE_SUCCESS)
                .message(LOAD_MUSIC_MESSSAGE_SUCCESS)
                .name("You had load music: "+ savedMusic.getName())
                .build();


    }

    @Override
    public MusicRespone updateMusic(MusicRequest musicRequest,Long id) {
            Optional<Music> music = musicRepository.findById(id);
            if(music==null){
                return MusicRespone.builder()
                        .code(UPDATE_MUSIC_CODE_FAIL)
                        .message(UPDATE_MUSIC_MESSSAGE_FAIL)
                        .name("Not found music with id: " + id)
                        .build();
            }
            Music updatemusic = music.get();
        updatemusic.setAuth(musicRequest.getAuth());
        updatemusic.setName(musicRequest.getName());
        updatemusic.setType(musicRequest.getType());
        updatemusic.setUrl(musicRequest.getUrl());
        updatemusic.setDescription(musicRequest.getDescription());
        musicRepository.save(updatemusic);
        return MusicRespone.builder()
                .code(UPDATE_MUSIC_CODE_SUCCESS)
                .message(UPDATE_MUSIC_MESSSAGE_SUCCESS)
                .name("You has updated music successfull: " + music.get().getName())
                .build();
    }

    @Override
    public void deleteMusicByName(String name) throws Exception {
        List<Music> music =  musicRepository.findByName(name);
        if(music ==null){
            throw new Exception("Music not found");
        }
        musicRepository.delete((Music) music);
        System.out.println("You has been deleted successfull: "+  ((Music) music).getName());

    }

    @Override
    public void deleteMusicById(long id) throws Exception {

        Optional<Music> music = musicRepository.findById(id);
        if(music == null){
            throw new Exception("Music not found");
        }
        musicRepository.delete(music.get());
        System.out.println("You has been deleted successfull: "+ music.get().getName());
    }

    @Override
    public List<Music> addMusicFavourties(long id) throws Exception {

        Optional<Music> music = musicRepository.findById(id);
        if(music == null){
             throw new Exception("Music not found");
        }
        Music musicFavouriste = music.get();

        List<Music> musicfavou = new ArrayList<>();
        musicfavou.add(musicFavouriste);


        return musicfavou;
    }

    @Override
    public List<Music> findMusicByName(String keyword) {
        return  musicRepository.findByName(keyword);
    }
}
