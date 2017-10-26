
import UIKit

class EliminarPeliculaViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
       setNavigationItems()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBAction func btnEliminar(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    
    func setNavigationItems(){
        self.navigationItem.title = "Detalle"
        let l_button = UIButton(type: UIButtonType.custom)
        l_button.setTitle("RETURN", for: UIControlState.normal)
        l_button.addTarget(self, action: #selector(EliminarPeliculaViewController.pressLeftButton), for: UIControlEvents.touchUpInside)
        l_button.sizeToFit()
        l_button.titleLabel!.font = UIFont(name: "Helvetica", size: 10)
        l_button.setTitleColor(UIColor.white, for: UIControlState.normal)
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(customView: l_button)
    }
    
    @objc func pressLeftButton(){
        self.navigationController?.popViewController(animated: true)
    }
   

}
