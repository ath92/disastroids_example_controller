package disastroids.disastroids_android;

import android.app.Application;
import disastroids.disastroids_android.model.Model;

/**
 * Responsible for getting and setting the model
 */

public class DisastroidsApplication extends Application {

        private Model model = new Model();

        public Model getModel() {
            return model;
        }

        public void setModel(Model model) {
            this.model = model;
        }

}
