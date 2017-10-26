
import UIKit

class LoginViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.setNavigationBarHidden(true, animated: false)

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
 
    }
    
    @IBAction func btnEnter(_ sender: Any) {
        let tabViewController = storyboard?.instantiateViewController(withIdentifier: "MainTabViewController")as! MainTabViewController
        tabViewController.selectedViewController = tabViewController.viewControllers?[1]
        present(tabViewController, animated: true, completion: nil)
    }
    

}
