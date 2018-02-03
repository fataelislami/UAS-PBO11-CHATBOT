package Interface;

import Model.Result;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface interCariMasjid {

    public void onSuccess(@NotNull List<Result> value);
}
