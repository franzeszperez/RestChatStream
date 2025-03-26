import SwiftUI
import shared

@main
struct RestChatApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    private var iosApp: IosApp?
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        iosApp = IosApp()
        return true
    }
    
    func application(
        _ application: UIApplication,
        configurationForConnecting connectingSceneSession: UISceneSession,
        options: UIScene.ConnectionOptions
    ) -> UISceneConfiguration {
        let configuration = UISceneConfiguration(name: nil, sessionRole: connectingSceneSession.role)
        configuration.delegateClass = SceneDelegate.self
        return configuration
    }
}

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    
    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = scene as? UIWindowScene else { return }
        
        if let appDelegate = UIApplication.shared.delegate as? AppDelegate,
           let iosApp = appDelegate.iosApp {
            iosApp.onCreate(application: UIApplication.shared, windowScene: windowScene)
        }
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard) // Kludge to prevent keyboard from pushing up the view
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let controller = UIViewController()
        controller.view.backgroundColor = .clear
        return controller
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
} 