import Foundation

@objc public class Elgin: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
