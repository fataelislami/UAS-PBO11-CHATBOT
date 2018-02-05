package Interface;

import Model.Content;
import Model.Result;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface interKajian {
    public void onSuccess(@NotNull List<Content> value);
}
