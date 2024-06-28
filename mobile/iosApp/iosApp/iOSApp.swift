import SwiftUI
import shared

@main
struct iOSApp: App {
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                SplashScreen()
            }
        }
	}
}

public class IosLegoraRequestsManager: LegoraRequestListener {
    public func getRequestHeaders() -> KotlinMutableDictionary<NSString, NSString> {
        return LegoraSharedStorage.shared.getApplicationHeaders(
            appVersion: "1.0",
            token: ApplicationDependenciesManager.shared.getStorageInstance()?.getAccessToken() ?? ""
        )
    }
    
    public func onUnAutherizedUser() {
        // Return User to Login Screen
    }
    
    
}
