package hfad.com.newretrofitpractice.utils

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import hfad.com.newretrofitpractice.data.Cemetery
import hfad.com.newretrofitpractice.data.Grave
import hfad.com.newretrofitpractice.domain.CemeteryDomainModel

@BindingAdapter("setCemeteryName")
fun TextView.setCemeteryName(item: CemeteryDomainModel?){
    item?.cemeteryName?.let {
        text = item.cemeteryName
    }
}

@BindingAdapter("setCemeteryLocation")
fun TextView.setCemeteryLocation(item: CemeteryDomainModel?){
    item?.cemeteryLocation?.let {
        text = item.cemeteryLocation
    }
}


@BindingAdapter("setGraveFirstName")
fun TextView.setGraveFirst(item: Grave?){
    item?.firstName?.let {
        text = item.firstName
    }
}

@BindingAdapter("setGraveLast")
fun TextView.setGraveLast(item: Grave?){
    item?.lastName?.let {
        text = item.lastName
    }

    Log.i("BindingAdapter", "setGrave last name called")
}

@BindingAdapter("setGraveBirth")
fun TextView.setGraveBirth(item: Grave?){
    item?.birthDate?.let {
        text = item.birthDate
    }
}

@BindingAdapter("setGraveDeath")
fun TextView.setGraveDeath(item: Grave?){
    item?.deathDate?.let {
        text = item.deathDate
    }
}

@BindingAdapter("setGraveMarried")
fun TextView.setGraveMarried(item: Grave?){
    item?.marriageYear?.let {
        text = item.marriageYear
    }
}

@BindingAdapter("setGraveComment")
fun TextView.setGraveComment(item: Grave?){
    item?.comment?.let {
        text = item.comment
    }
}

@BindingAdapter("setGraveNum")
fun TextView.setGraveNum(item: Grave?){
    item?.graveNumber?.let {
        text = item.graveNumber
    }
}
