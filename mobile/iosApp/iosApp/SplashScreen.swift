import SwiftUI

struct SplashScreen: View {
    
    @State private var shouldNavigate = false

	var body: some View {
        ZStack {
            Color("PrimaryColor")
                .ignoresSafeArea()
            
            if let image = UIImage(named: "SplashImage") {
                Image(uiImage: image)
                    .resizable()
                    .scaledToFill()
                        .frame(width: 500, height: 200, alignment: .center)
                        .clipped()
            }
            
            NavigationLink(destination: getNextScreenInstance(), isActive: $shouldNavigate) {
                EmptyView()
            }
        }
        .ignoresSafeArea()
        .onAppear {
            DispatchQueue.global(qos: .background).asyncAfter(deadline: .now() + 3.0) {
                DispatchQueue.main.async {
                    self.shouldNavigate = true
                }
            }
        }
	}
    
    @ViewBuilder
    private func getNextScreenInstance() -> some View {
        if LegoraApplicationUtils.isUserLoggedIn() {
            HomeScreen()
        } else {
            AuthScreen()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		SplashScreen()
	}
}
