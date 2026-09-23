//
//  SceneDelegate.swift
//  twoNavigationScreen
//
//  Created by Arwa Yaareb on 23/09/2026.
//
import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    // 1. Called when the app screen is being created and presented to the user
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        
        guard let windowScene = (scene as? UIWindowScene) else { return }
        
        let window = UIWindow(windowScene: windowScene)
        
        // Setup Screen 1 inside a Navigation Controller
        let profileVC = ProfileFormViewController()
        let navController = UINavigationController(rootViewController: profileVC)
        
        window.rootViewController = navController
        self.window = window
        window.makeKeyAndVisible()
    }

    // 2. Called when the system releases the scene (e.g., app in background destroyed)
    func sceneDidDisconnect(_ scene: UIScene) {
        // Called as the scene is being released by the system.
    }

    // 3. Called when the scene moves from an inactive state to an active state
    func sceneDidBecomeActive(_ scene: UIScene) {
        // Restart any tasks that were paused (or not yet started) when the scene was inactive.
    }

    // 4. Called when the scene will move from an active state to an inactive state
    func sceneWillResignActive(_ scene: UIScene) {
        // Occurs when user gets an incoming phone call or swiping away.
    }

    // 5. Called as the scene transitions from the background to the foreground
    func sceneWillEnterForeground(_ scene: UIScene) {
        // Undo the changes made on entering the background.
    }

    // 6. Called as the scene transitions from the foreground to the background
    func sceneDidEnterBackground(_ scene: UIScene) {
        // Save data, release shared resources, and store enough scene-specific state information.
    }
}
