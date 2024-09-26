package atula.web_music.service;

import atula.web_music.dto.MusicFavourtitesDto;
import atula.web_music.enetity.Music;
import atula.web_music.request.MusicRequest;
import atula.web_music.respone.MusicRespone;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicService {
    public MusicRespone loadMusic(MusicRequest musicRequest);
    public MusicRespone updateMusic(MusicRequest musicRequest, Long id);
    public void deleteMusicByName(String name) throws Exception;
    public void deleteMusicById(long id) throws Exception;
    public List<Music> addMusicFavourties(long id) throws Exception;
    public List<Music> findMusicByName( String keyword);
}
