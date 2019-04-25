package projekti.interact;

import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.MessageService;
import projekti.UserAccount;
import projekti.UserAccountService;
import projekti.Interactable;
import projekti.Message;
import projekti.MessageRepository;
import projekti.PictureService;
import projekti.Picture;

@Controller
public class CommentController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CommentService commentService;

    @PostMapping("/profile/{profileCode}/picture/{id}/addComment")
    public String addPictureComment(@PathVariable String profileCode, @PathVariable Long id, @RequestParam String pictureContent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();
        Picture p = pictureService.getOne(id);
        LocalDateTime dateTime = now();
        UserAccount writer = userAccountService.getUserAccountByUserName(loggedInUsername);
        Comment comment = new Comment(writer, p, dateTime, pictureContent);
        commentService.addComment(comment);
        return "redirect:/profile/" + profileCode;
    }

    @PostMapping("/profile/{profileCode}/message/{id}/addComment")
    public String addComment(@PathVariable String profileCode, @PathVariable Long id, @RequestParam String commentContent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();
        Message m = messageRepository.getOne(id);
        LocalDateTime dateTime = now();
        UserAccount writer = userAccountService.getUserAccountByUserName(loggedInUsername);
        Comment comment = new Comment(writer, m, dateTime, commentContent);
        commentService.addComment(comment);
        return "redirect:/profile/" + profileCode;
    }
}
/*   @PostMapping("/{profileCode}/message/{id}/addThumbUp")
    public String addThumbUp(@PathVariable String profileCode, @PathVariable Long id) {


                                                    <form th:action="@{/message/{id}/addComment(id=${message.id})}" th:method="POST">
                                                        <input type="text" name="comment" id="caption" placeholder="..."/>
                                                        <input type="submit" value="Add comment" />
                                                    </form>
                                                </th>
                                                <th>
                                                    <form th:action="@{/message/{id}/addThumbUp(id=${message.id})}" th:method="POST">
 */