// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "OniiCapacitorPluginElgin",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "OniiCapacitorPluginElgin",
            targets: ["ElginPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "ElginPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ElginPlugin"),
        .testTarget(
            name: "ElginPluginTests",
            dependencies: ["ElginPlugin"],
            path: "ios/Tests/ElginPluginTests")
    ]
)