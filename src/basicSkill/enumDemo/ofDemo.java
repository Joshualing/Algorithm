package basicSkill.enumDemo;

enum Scene {
    CLIENT(0, "客户端"),
    SHOP(1, "卖家端");

    private int scene;
    private String desc;

    Scene(int scene, String desc) {
        this.scene = scene;
        this.desc = desc;
    }

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "scene=" + scene +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static Scene of(int scene) {
        for (Scene s : Scene.values()) {
            if (s.getScene() == scene)
                return s;
        }
        return CLIENT;
    }
}

public class ofDemo {
    public static void main(String[] args) {
        System.out.println(Scene.of(1));
        System.out.println(Scene.of(1).name());
    }
}
