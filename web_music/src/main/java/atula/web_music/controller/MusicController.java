package atula.web_music.controller;

import atula.web_music.enetity.Music;
import atula.web_music.repository.MusicRepository;
import atula.web_music.request.MusicRequest;
import atula.web_music.respone.MusicRespone;
import atula.web_music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MusicController {
    @Autowired
    private MusicService musicService;
    @Autowired
    private MusicRepository musicRepository;
    @PostMapping("/user/loadmusic")
    public MusicRespone loadMusic(
            @RequestHeader("Authorization") String jwt,
            @RequestBody MusicRequest musicRequest){
        return musicService.loadMusic(musicRequest);
    }
    @PostMapping("/user/updatemusic/{id}")
    public MusicRespone updateMusic(
            @RequestHeader("Authorization") String jwt,
            @RequestBody MusicRequest musicRequest,
            @PathVariable Long id )
    {
        return musicService.updateMusic(musicRequest, id);
    }


// fix
    @DeleteMapping("/user/deletemusicByName")
    public void deletemusicByName(
            @RequestHeader("Authorization") String jwt,
            @Param("keyword") String keyword) throws Exception {
         musicService.deleteMusicByName(keyword);

    }


    @DeleteMapping("/user/deletemusicById/{id}")
    public void deletemusicById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {
        musicService.deleteMusicById(id);

    }

    @GetMapping("/findmusicbyname")
    public List<Music> findMusicByName(
            @RequestParam String keyword){
        return musicService.findMusicByName(keyword);
    }




}
