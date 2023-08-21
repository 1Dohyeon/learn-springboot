package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }


    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro") // localhost:8080/board/writepro
    public String boardWritePro(Board board, Model model) {
        // boardService 클래스의 메서드를 이용하여 데이터 저장
        boardService.write(board);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")  // localhost:8080/board/list
    public String boardList(Model model) {
        // springframework의 ui인 Model을 이용하여 board타입의 리스트 데이터를
        // 가져오는데 list라는 변수로 담음. -> boardlist.html에서 이용.
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }

    @GetMapping("/board/view")  // localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id, Model model) {
        boardService.boardDelete(id);

        model.addAttribute("message", "글을 삭제하였습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";

    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {
        // 여기서 PathVariable이란 매핑 어노테이션에 있는 {} 안에 있는 값 id를
        // 읽고 Integer 타입의 id로 파라미터를 받겠다는 의미
        // ?id=1 <- 이 방식이 아닌 깔끔하게 /1 이런식으로 나옴

        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model) {

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        model.addAttribute("message", "글을 수정하였습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }


}
