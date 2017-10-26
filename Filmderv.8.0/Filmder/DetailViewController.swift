
import UIKit

class DetailViewController: UIViewController {
    @IBOutlet weak var detailImage: UIImageView!
    var imageName : String!

    override func viewDidLoad() {
        super.viewDidLoad()
        detailImage.image = UIImage(named: self.imageName )
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func comonInit(_ imageName:String,title:String){
        self.imageName = imageName
        self.title = title
    }
    


}
